package io.codelirium.constraints.einstein.entity;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static org.springframework.util.Assert.notNull;


public class EnumEntities {


	private EnumEntities() { }


	public enum Colour {

		RED,
		GREEN,
		YELLOW,
		WHITE,
		BLUE;

	}


	public enum Cigarette {

		PALL_MALL,
		DUNHILL,
		BLENDS,
		BLUEMASTER,
		PRINCE;

	}


	public enum Nationality {

		BRIT,
		SWEDE,
		DANE,
		NORWEGIAN,
		GERMAN;

	}


	public enum Drink {

		TEA,
		COFFEE,
		MILK,
		BEER,
		WATER;

	}


	public enum Pet {

		DOG,
		BIRD,
		CAT,
		HORSE,
		FISH;

	}


	public static List<String> reflect(final Class<? extends Enum<?>> enumClazz) {

		notNull(enumClazz, "The enum class cannot be null.");


		return of(Arrays
					.toString(enumClazz.getEnumConstants())
						.replaceAll("^.|.$", "")
						.split(", "))
								.collect(toList());

	}
}
