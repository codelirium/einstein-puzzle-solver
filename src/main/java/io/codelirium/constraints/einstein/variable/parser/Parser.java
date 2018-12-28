package io.codelirium.constraints.einstein.variable.parser;

import java.util.List;
import java.util.stream.Stream;


public interface Parser<V, M> {

	List<V> parse(final M model, final Stream<Class<? extends Enum<?>>> enumClazzesStream);

}
