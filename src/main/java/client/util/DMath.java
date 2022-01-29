package client.util;

import java.math.BigInteger;

// Credits to Runelite
public class DMath
{
	public static BigInteger modInverse(BigInteger val, int bits)
	{
		try
		{
			BigInteger shift = BigInteger.ONE.shiftLeft(bits);
			return val.modInverse(shift);
		}
		catch (ArithmeticException e)
		{
			return val;
		}
	}

	public static int modInverse(int val)
	{
		return modInverse(BigInteger.valueOf(val), 32).intValue();
	}

	public static long modInverse(long val)
	{
		return modInverse(BigInteger.valueOf(val), 64).longValue();
	}

	public static Number modInverse(Number value)
	{
		if (value instanceof Integer)
		{
			return modInverse((int) value);
		}
		else if (value instanceof Long)
		{
			return modInverse((long) value);
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	public static boolean isInversable(int val)
	{
		try
		{
			modInverse(val);
			return true;
		}
		catch (ArithmeticException ex)
		{
			return false;
		}
	}

	private static boolean isInversable(long val)
	{
		try
		{
			modInverse(val);
			return true;
		}
		catch (ArithmeticException ex)
		{
			return false;
		}
	}

	public static boolean isInversable(Number value)
	{
		if (value instanceof Integer)
		{
			return isInversable((int) value);
		}
		else if (value instanceof Long)
		{
			return isInversable((long) value);
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	public static Number multiply(Number one, Number two)
	{
		assert one.getClass() == two.getClass();

		if (one instanceof Integer)
		{
			return (int) one * (int) two;
		}
		else if (one instanceof Long)
		{
			return (long) one * (long) two;
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	public static boolean equals(Number one, int two)
	{
		if (one instanceof Long)
		{
			return equals(one, ((long) two) & 0xffffffff);
		}
		return one.intValue() == two;
	}

	public static boolean equals(Number one, long two)
	{
		if (one instanceof Integer)
		{
			return equals(one, (int) two);
		}
		return one.longValue() == two;
	}
}
