package com.aurora.sample.db.entity;

import java.io.Serializable;


import lombok.Data;

/**
 * <p>
 * <p>
 * <p>
 * <p>
 * @author cjrequena
 * @version 1.0
 * @since JDK1.8
 * @see
 *
 */
@Data
public class HotelEntity  implements Serializable {

	private Integer hotelId;
	private String name;
	private String email;
	private String phone;
	private String fax;
	private String rate;
	private String localCurrencyCode;
	private String description;

}
