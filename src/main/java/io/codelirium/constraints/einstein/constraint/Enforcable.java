package io.codelirium.constraints.einstein.constraint;

import java.util.List;


public interface Enforcable<M, V> {

	void enforce(final M model, final List<V> list);

}
