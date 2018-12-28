package io.codelirium.constraints.einstein.solver;

import java.util.List;


public interface ConstraintSolver<M, V> {

	void solve(final M model, final List<V> list);

}
