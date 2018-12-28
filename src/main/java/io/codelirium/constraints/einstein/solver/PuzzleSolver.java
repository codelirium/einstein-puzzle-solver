package io.codelirium.constraints.einstein.solver;

import io.codelirium.constraints.einstein.dto.ModelVariablesDTO;
import io.codelirium.constraints.einstein.entity.EnumEntities.Nationality;
import io.codelirium.constraints.einstein.variable.locator.ModelVariablesLocator;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.search.loop.monitors.IMonitorSolution;
import org.chocosolver.solver.variables.IntVar;
import org.springframework.stereotype.Component;
import javax.inject.Inject;
import java.util.List;

import static io.codelirium.constraints.einstein.entity.EnumEntities.Pet.FISH;
import static io.codelirium.constraints.einstein.entity.EnumEntities.indexOf;
import static java.lang.System.out;
import static org.springframework.util.Assert.notNull;


@Component
public class PuzzleSolver implements ConstraintSolver<Model, ModelVariablesDTO> {


	private ModelVariablesLocator locator;


	@Inject
	public PuzzleSolver(final ModelVariablesLocator locator) {

		this.locator = locator;

	}


	@Override
	public void solve(final Model model, final List<ModelVariablesDTO> variablesList) {

		notNull(model, "The model cannot be null.");

		notNull(variablesList, "The list of variables cannot be null.");


		final IntVar fishValue = locator.locate(variablesList, FISH.name()).get();

		final Solution solution = new Solution(model, fishValue);


		final Solver solver = model.getSolver();

		solver.plugMonitor((IMonitorSolution) solution::record);

		while (solver.solve());


		out.println();

		out.printf("The fish owner is the %s%n", indexOf(Nationality.class, solution.getIntVal(fishValue)));

		out.println();

		solver.printShortStatistics();
	}
}
