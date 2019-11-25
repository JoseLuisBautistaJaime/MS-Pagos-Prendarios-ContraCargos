/*
 *
 * Microservicios - Mi Monte
 *
 * <p><b>Quarksoft Copyrigth © 2019</b></p>
 *
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.util.Assert;

import mx.com.nmp.pagos.mimonte.util.DateUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Utileria para generar una sentencia INSERT o CALL de SQL.
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 * @author <a href="https://wiki.quarksoft.net/display/~jgalvez">JGALVEZ</a>
 */
public class ReporteJdbcBulkInsert<T extends Object> {
	private static final String QUERY_INSERT = "INSERT INTO ";
	private static final String QUERY_VALUES = " VALUES";
	private static final String CALL_SP = "SELECT ";

	private static final String TYPE_SEP = "\"";
	private static final String COMA = ", ";
	private static final String PUNTO_COMA = "; ";
	private static final String PAR_LEF = "(";
	private static final String PAR_RIGHT = ")";

	private List<? extends T> lista;
	private Set<JdbcColumnDef> columnsDef;


	/**
	 * Constructor.
	 */
	public ReporteJdbcBulkInsert(List<? extends T> lista, boolean incluyeId) {
		super();
		usingValues(lista, incluyeId);
		validate();
	}


	/**
	 * Genera el Query apartir de las propiedades establecidas.
	 * @return Sentencia INSERT.
	 */
	public String buildInsertQuery() {
		String valuesStr = buildValues();
		String tableName = getTableName(lista.get(0).getClass());
		String columns = buildColumns();

		return QUERY_INSERT + tableName + PAR_LEF + columns + PAR_RIGHT + QUERY_VALUES + valuesStr;
	}


	/**
	 * Genera el Query apartir de las propiedades establecidas.
	 * @return Sentencia INSERT.
	 */
	public String buildInsertStatement() {
		String tableName = getTableName(lista.get(0).getClass());
		String columns = buildColumns();
		String columnsParams = buildParams(); 
		return QUERY_INSERT + tableName + PAR_LEF + columns + PAR_RIGHT + QUERY_VALUES + PAR_LEF + columnsParams + PAR_RIGHT;
	}


	/**
	 * Genera el Query usando un SP.
	 * @param storedProcedure
	 * @return Sentencia CALL.
	 */
	public String buildCallSP(String storedProcedure) {
		String valuesStr = buildValues(storedProcedure);
		return valuesStr;
	}


	/**
	 * Genera el listado de valores apartir de las propiedades establecidas.
	 * @return SqlParameterSource array.
	 */
	public SqlParameterSource[] buildBatchValues() {
		List<SqlParameterSource> params = buildListParams();
		return params != null ? params.toArray(new SqlParameterSource[0]) : null;
	}


	/**
	 * Especifica los valores que seran insertados.
	 *
	 * @param lista Lista de movimientos a insertar.
	 * @param incluyeId
	 */
	private void usingValues(List<? extends T> lista, boolean incluyeId) {
		this.lista = lista;
		this.columnsDef = extractColumnsDef(lista.get(0), incluyeId);
	}


	/**
	 * Especifica los valores que seran insertados.
	 */
	private String buildValues() {

		StringBuilder val = new StringBuilder();
		Iterator<? extends T> it = lista.iterator();

		while (it.hasNext()) {
			T row = it.next();
			val.append(gerateSingleValue(row));
			if (it.hasNext()) {
				val.append(COMA);
			}
		}

		return val.toString();
	}


	/**
	 * Especifica los valores que seran insertados.
	 *
	 * @param storedProcedure SP name
	 */
	private String buildValues(String storedProcedure) {

		StringBuilder val = new StringBuilder();
		Iterator<? extends T> it = lista.iterator();

		val.append(CALL_SP).append(storedProcedure);
		while (it.hasNext()) {
			T row = it.next();
			val.append(gerateSingleValue(row));
			if (it.hasNext()) {
				val.append(PUNTO_COMA).append(CALL_SP).append(storedProcedure);
			}
		}

		return val.append(PUNTO_COMA).toString();
	}


	/**
	 * Especifica los valores que seran insertados.
	 * @return List parameters
	 */
	private List<SqlParameterSource> buildListParams() {

		List<SqlParameterSource> listValores = new ArrayList<SqlParameterSource>();
		Iterator<? extends T> it = lista.iterator();
		while (it.hasNext()) {
			T row = it.next();
			SqlParameterSource values = generateValues(row);
			listValores.add(values);
		}

		return listValores;
	}


	private void validate() {
		Assert.notEmpty(this.lista, "Los valores a insertar son requeridos");
		Assert.notEmpty(this.columnsDef, "Las columnas de la entidad son incorrectas");
	}



	private String gerateSingleValue(T movimiento) {

		BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(movimiento);
		Iterator<JdbcColumnDef> iteratorColumnDef = columnsDef.iterator();

		StringBuilder val = new StringBuilder(PAR_LEF);
		while (iteratorColumnDef.hasNext()) {
			JdbcColumnDef columnDef = iteratorColumnDef.next();
			val.append(getBeanValueString(beanWrapper, columnDef));
			if (iteratorColumnDef.hasNext()) {
				val.append(COMA);
			}
		}
		val.append(PAR_RIGHT);

		return val.toString();
	}


	private SqlParameterSource generateValues(T movimiento) {
		MapSqlParameterSource values = new MapSqlParameterSource();

		BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(movimiento);
		Iterator<JdbcColumnDef> iteratorColumnDef = columnsDef.iterator();

		while (iteratorColumnDef.hasNext()) {
			JdbcColumnDef columnDef = iteratorColumnDef.next();
			values.addValue(buildParamName(columnDef.parentProperty, columnDef.propertyName), getBeanValue(beanWrapper, columnDef));
		}

		return values;
	}


	/**
	 * Obtiene el nombre de la tabla
	 * @param clazz
	 * @return
	 */
	private String getTableName(Class<?> clazz) {
		Table tabla = clazz.getAnnotation(Table.class);
		return tabla != null ? tabla.name() : null;
	}


	/**
	 * Extrae la definicion de las columnas de la tabla: propiedad, columna y tipo
	 * @param T
	 * @param incluyeId
	 * @return
	 */
	private Set<JdbcColumnDef> extractColumnsDef(T baseEntity, boolean incluyeId) {
		Class<?> clazz = baseEntity.getClass();
		// Base fields
		Field[] baseFields = clazz.getDeclaredFields();
		// Check parent class
		Field[] parentFields = clazz.getSuperclass() != null ? clazz.getSuperclass().getDeclaredFields() : new Field[0];
		// Updatable
		Field[] updatableFields = clazz.getSuperclass() != null && clazz.getSuperclass().getSuperclass() != null ? clazz.getSuperclass().getSuperclass().getDeclaredFields() : new Field[0];
		// Merge
		Field[] fields = (Field[]) ArrayUtils.addAll(baseFields, parentFields);
		fields = (Field[]) ArrayUtils.addAll(fields, updatableFields);

		return extractColumnsDef("", fields, incluyeId);
	}


	/**
	 * Extrae la definicion de las columnas de la tabla: propiedad, columna y tipo
	 * @param parentProperty
	 * @param fields
	 * @param incluyeId
	 * @return
	 */
	private Set<JdbcColumnDef> extractColumnsDef(String parentProperty, Field[] fields, boolean incluyeId) {
		Set<JdbcColumnDef> columnsDef = new LinkedHashSet<JdbcColumnDef>();
		for (Field field : fields) {
			Column column = field.getAnnotation(Column.class);
			Id identity = field.getAnnotation(Id.class);
			Temporal temporal = field.getAnnotation(Temporal.class);
			Embedded embedded = field.getAnnotation(Embedded.class);
			JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);
			// No IDENTITY
			if (identity == null || incluyeId) {
				if (column != null) {
					JdbcColumnDef columnDef = new JdbcColumnDef();
					columnDef.columnName = column.name();
					columnDef.propertyName = field.getName();
					columnDef.columnType = JdbcColumnType.get(field.getType().getSimpleName());
					columnDef.parentProperty = parentProperty;
					if (columnDef.columnType == JdbcColumnType.Date) {
						if (temporal != null && temporal.value() == TemporalType.TIMESTAMP) {
							columnDef.columnType = JdbcColumnType.Timestamp;
						}
					}
					columnsDef.add(columnDef);
				}
				else if (joinColumn != null) { // Only ID
					JdbcColumnDef columnDef = new JdbcColumnDef();
					columnDef.columnName = joinColumn.name();
					columnDef.propertyName = "id";
					columnDef.columnType = JdbcColumnType.get(field.getType().getSimpleName());
					columnDef.parentProperty = field.getName();
					if (columnDef.columnType == JdbcColumnType.Date) {
						if (temporal != null && temporal.value() == TemporalType.TIMESTAMP) {
							columnDef.columnType = JdbcColumnType.Timestamp;
						}
					}
					columnsDef.add(columnDef);
				}
				else if (embedded != null) {
					// Extract embedded class fields
					Set<JdbcColumnDef> embeddedColumnsDef = extractColumnsDef(field.getName(), field.getType().getDeclaredFields(), incluyeId);
					if (embeddedColumnsDef != null) {
						columnsDef.addAll(embeddedColumnsDef);
					}
				}
			}
		}			
		return columnsDef;
	}


	/**
	 * Obtiene el valor de la propiedad completa incluyendo la clase padre
	 * @param parentProperty
	 * @param propertyName
	 * @return
	 */
	private String buildFieldPropertyName(String parentProperty, String propertyName) {
		return (StringUtils.isNotBlank(parentProperty) ? (parentProperty+ ".") : "") + propertyName;
	}


	/**
	 * Obtiene el valor de la propiedad del bean
	 * @param beanWrapper
	 * @param columnDef
	 * @return
	 */
	private Object getBeanValue(BeanWrapper beanWrapper, JdbcColumnDef columnDef) {
		
		// Validate parent property name
		Object propertyValue = null;
		if (StringUtils.isNotBlank(columnDef.parentProperty)) {
			// Check nulls
			if (beanWrapper.getPropertyValue(columnDef.parentProperty) != null) {
				propertyValue = beanWrapper.getPropertyValue(buildFieldPropertyName(columnDef.parentProperty, columnDef.propertyName));
			}
		}
		else {
			propertyValue = beanWrapper.getPropertyValue(columnDef.propertyName);
		}
		return propertyValue;
	}


	/**
	 * Obtiene el valor de la propiedad del bean
	 * @param beanWrapper
	 * @param columnDef
	 * @return
	 */
	private Object getBeanValueString(BeanWrapper beanWrapper, JdbcColumnDef columnDef) {
		
		// Validate parent property name
		Object propertyValue = null;
		if (StringUtils.isNotBlank(columnDef.parentProperty)) {
			// Check nulls
			if (beanWrapper.getPropertyValue(columnDef.parentProperty) != null) {
				propertyValue = beanWrapper.getPropertyValue(buildFieldPropertyName(columnDef.parentProperty, columnDef.propertyName));
			}
		}
		else {
			propertyValue = beanWrapper.getPropertyValue(columnDef.propertyName);
		}

		String value = TYPE_SEP + TYPE_SEP;
		switch (columnDef.columnType) {
			case BigDecimal:
			case Long:
			case Float:
			case Integer:
				value = propertyValue != null ? propertyValue.toString() : "null";
				break;
			case Boolean:
				value = propertyValue != null ? (new Boolean(propertyValue.toString()) ? "1" : "0") : "null";
				break;
			case String:
				value = propertyValue != null ? (TYPE_SEP + cleanString(propertyValue.toString()) + TYPE_SEP) : "null";
				break;
			case Date:
				value = propertyValue != null ? (TYPE_SEP + DateUtil.formatDate((Date)propertyValue, "yyyy-MM-dd") + TYPE_SEP) : "null";
				break;
			case Timestamp:
				value = propertyValue != null ? (TYPE_SEP + DateUtil.formatDate((Date)propertyValue, "yyyy-MM-dd HH:mm:ss") + TYPE_SEP) : "null";
				break;
		}
		return value;
	}


	/**
	 * Crea el listado de columnas separadas por ,
	 * @return
	 */
	private String buildColumns() {
		Set<String> columns = new LinkedHashSet<String>();
		for (JdbcColumnDef columnDef : this.columnsDef) {
			columns.add(columnDef.columnName);
		}
		return StringUtils.join(columns, COMA);
	}


	/**
	 * Crea el listado de columnas separadas por ,
	 * @param columnsDef
	 * @return
	 */
	private String buildParams() {
		List<String> columns = new ArrayList<String>();
		for (JdbcColumnDef columnDef : columnsDef) {
			columns.add(":" + buildParamName(columnDef.parentProperty, columnDef.propertyName));
		}
		return StringUtils.join(columns, COMA);
	}


	/**
	 * Construyer el nombre del parametro en base a la propiedad base y propiedad 
	 * @param parentProperty
	 * @param propertyName
	 * @return
	 */
	private String buildParamName(String parentProperty, String propertyName) {
		return StringUtils.isNotBlank(parentProperty) ? (parentProperty + WordUtils.capitalize(propertyName)) : propertyName;
	}


	/**
	 * Filteres 
	 * @param string
	 * @return
	 */
	private String cleanString(String value) {
		String clean = "";
		if (value != null) {
			clean = Matcher.quoteReplacement(value);
		}
		return clean;
	}


	/**
	 * Jdbc Columns definition
	 */
	public static class JdbcColumnDef {
		public String columnName;
		public String propertyName;
		public JdbcColumnType columnType;
		public String parentProperty;
	}
	
	public static enum JdbcColumnType {
		String,
		Long,
		Boolean,
		Timestamp,
		BigDecimal,
		Integer,
		Float,
		Date;
		public static JdbcColumnType get(String typeName) {
			JdbcColumnType type = JdbcColumnType.Long; // Default
			for (JdbcColumnType jdbcType : JdbcColumnType.values()) {
				if (jdbcType.name().equalsIgnoreCase(typeName)) {
					type = jdbcType;
					break;
				}
			}
			return type;
		}
	}

}
