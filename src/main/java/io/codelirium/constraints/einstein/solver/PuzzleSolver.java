package io.codelirium.constraints.einstein.solver;

import io.codelirium.constraints.einstein.dto.ModelVariablesDTO;
import io.codelirium.constraints.einstein.entity.EnumEntities.Nationality;
import io.codelirium.constraints.einstein.variable.locator.ModelVariablesLocator;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.search.limits.SolutionCounter;
import org.chocosolver.solver.variables.IntVar;
import org.springframework.stereotype.Component;
import javax.inject.Inject;
import java.util.List;

import static io.codelirium.constraints.einstein.entity.EnumEntities.Pet.FISH;
import static java.lang.System.out;
import static java.util.Arrays.stream;
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


		final Solver solver = model.getSolver();

		final Solution solution = solver.findSolution(new SolutionCounter(model, 1));


		final IntVar fishValue = locator.locate(variablesList, FISH.name()).get();

		final String fishOwner = stream(Nationality.values())
									.filter(nationalityEnum -> locator.locate(variablesList, nationalityEnum.name()).get().getValue() == fishValue.getValue())
									.findAny()
										.get()
											.name();


		out.println();

		out.printf("The fish owner is the %s.%n", fishOwner);

		out.println();

		solver.printShortStatistics();
	}
}
