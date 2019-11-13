/*
 *
 * Microservicios - Mi Monte
 *
 * <p><b>Quarksoft Copyrigth © 2019</b></p>
 *
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc;

import org.apache.commons.lang.StringUtils;
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

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Utileria para generar una sentencia INSERT de SQL.
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 * @author <a href="https://wiki.quarksoft.net/display/~jgalvez">JGALVEZ</a>
 */
public class ReporteJdbcBulkInsert<T extends Object> {
	private static final String QUERY_INSERT = "INSERT INTO ";
	private static final String QUERY_VALUES = " VALUES";

	private static final String TYPE_SEP = "\"";
	private static final String COMA = ", ";
	private static final String PAR_LEF = "(";
	private static final String PAR_RIGHT = ")";

	private String tableName;
	private String valuesStr;
	private List<SqlParameterSource> listValores;
	private Set<JdbcColumnDef> columnsDef;


	/**
	 * Constructor.
	 */
	public ReporteJdbcBulkInsert(List<? extends T> lista) {
		super();
		usingValues(lista);
		validate();
	}


	/**
	 * Genera el Query apartir de las propiedades establecidas.
	 * @return Sentencia INSERT.
	 */
	public String buildInsertQuery() {
		String columns = buildColumns();
		return QUERY_INSERT + tableName + PAR_LEF + columns + PAR_RIGHT + QUERY_VALUES + valuesStr;
	}


	/**
	 * Genera el Query apartir de las propiedades establecidas.
	 * @return Sentencia INSERT.
	 */
	public String buildInsertStatement() {
		String columns = buildColumns();
		String columnsParams = buildParamNames(); 
		return QUERY_INSERT + tableName + PAR_LEF + columns + PAR_RIGHT + QUERY_VALUES + PAR_LEF + columnsParams + PAR_RIGHT;
	}


	/**
	 * Genera el listado de valores apartir de las propiedades establecidas.
	 * @return Sentencia INSERT.
	 */
	public SqlParameterSource[] buildBatchValues() {
		return this.listValores.toArray(new SqlParameterSource[0]);
	}


	/**
	 * Especifica los valores que seran insertados.
	 *
	 * @param lista Lista de movimientos a insertar.
	 */
	private void usingValues(List<? extends T> lista) {

		this.valuesStr = "";
		this.listValores = new ArrayList<SqlParameterSource>();
		this.tableName = getTableName(lista.get(0).getClass());
		this.columnsDef = extractColumnsDef(lista.get(0));

		StringBuilder val = new StringBuilder();
		Iterator<? extends T> it = lista.iterator();

		while (it.hasNext()) {
			T row = it.next();
			SqlParameterSource values = generateValues(row);

			val.append(gerateSingleValue(row));
			if (it.hasNext()) {
				val.append(COMA);
			}

			listValores.add(values);
		}

		valuesStr = val.toString();
	}


	private void validate() {
		Assert.notNull(tableName, "El nombre de la tabla es requerido");
		Assert.notEmpty(listValores, "Los valores a insertar son requeridos");
		Assert.notEmpty(columnsDef, "Faltan propiedade a recuperar");
	}



	private String gerateSingleValue(T movimientoReporte) {
		StringBuilder val = new StringBuilder(PAR_LEF);

		BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(movimientoReporte);
		Iterator<JdbcColumnDef> iteratorColumnDef = columnsDef.iterator();

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


	private SqlParameterSource generateValues(T movimientoReporte) {
		MapSqlParameterSource values = new MapSqlParameterSource();

		BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(movimientoReporte);
		Iterator<JdbcColumnDef> iteratorColumnDef = columnsDef.iterator();

		while (iteratorColumnDef.hasNext()) {
			JdbcColumnDef columnDef = iteratorColumnDef.next();
			values.addValue(columnDef.propertyName, getBeanValue(beanWrapper, columnDef));
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
	 * @return
	 */
	private Set<JdbcColumnDef> extractColumnsDef(T baseEntity) {
		Field[] fields = baseEntity.getClass().getDeclaredFields();
		return extractColumnsDef("", fields);
	}


	/**
	 * Extrae la definicion de las columnas de la tabla: propiedad, columna y tipo
	 * @param fields
	 * @return
	 */
	private Set<JdbcColumnDef> extractColumnsDef(String parentProperty, Field[] fields) {
		Set<JdbcColumnDef> columnsDef = new LinkedHashSet<JdbcColumnDef>();
		for (Field field : fields) {
			Column column = field.getAnnotation(Column.class);
			Id identity = field.getAnnotation(Id.class);
			Temporal temporal = field.getAnnotation(Temporal.class);
			Embedded embedded = field.getAnnotation(Embedded.class);
			// No IDENTITY
			if (identity == null) {
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
				else if (embedded != null) {
					// Extract embedded class fields
					Set<JdbcColumnDef> embeddedColumnsDef = extractColumnsDef(field.getName(), field.getType().getDeclaredFields());
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
				value = propertyValue != null ? (TYPE_SEP + propertyValue.toString() + TYPE_SEP) : "null";
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
	 * @param columnsDef
	 * @return
	 */
	private String buildColumns() {
		Set<String> columns = new LinkedHashSet<String>();
		for (JdbcColumnDef columnDef : columnsDef) {
			columns.add(columnDef.columnName);
		}
		return StringUtils.join(columns, COMA);
	}


	/**
	 * Crea el listado de columnas separadas por ,
	 * @param columnsDef
	 * @return
	 */
	private String buildParamNames() {
		List<String> columns = new ArrayList<String>();
		//for (int i = 1; i <= columnsDef.size(); i++) {
		for (JdbcColumnDef columnDef : columnsDef) {
			columns.add(":" + columnDef.propertyName);
			//columns.add("?");
		}
		return StringUtils.join(columns, COMA);
	}


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
			JdbcColumnType type = JdbcColumnType.String;
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
