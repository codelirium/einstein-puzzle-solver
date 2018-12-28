package io.codelirium.constraints.einstein;

import io.codelirium.constraints.einstein.constraint.FactEnforcer;
import io.codelirium.constraints.einstein.constraint.HintEnforcer;
import io.codelirium.constraints.einstein.dto.ModelVariablesDTO;
import io.codelirium.constraints.einstein.entity.EnumEntities.*;
import io.codelirium.constraints.einstein.solver.PuzzleSolver;
import io.codelirium.constraints.einstein.variable.parser.ModelVariablesParser;
import org.chocosolver.solver.Model;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import javax.inject.Inject;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.util.stream.Stream.of;
import static org.springframework.boot.Banner.Mode.OFF;


@SpringBootApplication
public class EinsteinPuzzleSolverApplication implements CommandLineRunner {


	@Inject
	private ModelVariablesParser parser;

	@Inject
	private FactEnforcer factEnforcer;

	@Inject
	private HintEnforcer hintEnforcer;

	@Inject
	private PuzzleSolver solver;


	public static void main(final String[] args) {

		new SpringApplicationBuilder(EinsteinPuzzleSolverApplication.class)
				.logStartupInfo(FALSE)
				.bannerMode(OFF)
				.run(args);

	}


	@Override
	public void run(final String ... args) throws Exception {

		final Model model = new Model("Einstein Puzzle");


		final List<ModelVariablesDTO> variables = parser.parse(model, of(Nationality.class,
																		 Colour.class,
																		 Cigarette.class,
																		 Drink.class,
																		 Pet.class));


		factEnforcer.enforce(model, variables);

		hintEnforcer.enforce(model, variables);


		solver.solve(model, variables);
	}
}
