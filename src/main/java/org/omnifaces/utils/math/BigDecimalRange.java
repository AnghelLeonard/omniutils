package org.omnifaces.utils.math;

import java.io.Serializable;
import java.math.BigDecimal;

@Deprecated
public class BigDecimalRange extends Range<BigDecimal> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static BigDecimalRange of(BigDecimal min, BigDecimal max) {
		return (BigDecimalRange) Range.of(BigDecimal.class, min, max);
	}

	@Override
	protected BigDecimalRange newInstance() {
		return new BigDecimalRange();
	}

}
