package io.codelirium.constraints.einstein.variable.locator;

import java.util.List;
import java.util.Optional;


public interface Locator<V1, V2, T> {

	Optional<V1> locate(final List<V2> list, final T label);

}
