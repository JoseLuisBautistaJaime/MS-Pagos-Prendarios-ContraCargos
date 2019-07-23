/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.util.validacion;

import java.util.regex.PatternSyntaxException;

import mx.com.nmp.pagos.mimonte.constans.RegexConstants;

/**
 * @name ValidadorGenerico
 * @description Clase abstracta que contiene metodos estaticos para relaizar
 *              validaciones genericas sobre diversos tipos de objetos,
 *              principalmente evalua cadenas de caracteres y patrones que se
 *              desea existan en estas
 *
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 07/03/2019 16:42 hrs.
 * @version 0.1
 */
public interface ValidadorGenerico {

	/**
	 * Evalua si una direccion de email es correcta en base a un patron y regresa un
	 * valor booleano indicando el resultado
	 * 
	 * @param email
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static boolean validateEmail(final String email) throws PatternSyntaxException {
		return null != email && email.matches(RegexConstants.REGEX_EMAIL);
	}

	/**
	 * Evalua si un patron de email es valido
	 * 
	 * @param email
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static boolean validateEmail2(final String email) throws PatternSyntaxException {
		if (null == email || email.equals(""))
			return false;
		if (email.contains("@@") || email.contains(".."))
			return false;
		else {
			String[] arr = email.split("@");
			if (arr.length != 2) {
				return false;
			} else {
				String[] arr2 = arr[1].split("\\.");
				if (arr2.length != 2) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Evalua si un numero telfonico es correcto en base a un patron y regresa un
	 * valor booleano indicando el resultado
	 * 
	 * @param phoneNumber
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static boolean validatePhoneNumber(final String phoneNumber) throws PatternSyntaxException {
		return null != phoneNumber && phoneNumber.matches(RegexConstants.REGEX_PHONE_NUMBER);
	}

	/**
	 * Evalua si una cadena de caracteres no contiene caracteres especiales en base
	 * a un patron y regresa un valor booleano indicando el resultado
	 * 
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static boolean validateNoSpecialChars(final String str) throws PatternSyntaxException {
		return null != str && str.matches(RegexConstants.REGEX_NO_SPECIAL_CHARACTERS);
	}

}
