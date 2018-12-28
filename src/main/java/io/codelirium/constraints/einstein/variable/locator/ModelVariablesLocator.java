package io.codelirium.constraints.einstein.variable.locator;

import io.codelirium.constraints.einstein.dto.ModelVariablesDTO;
import org.chocosolver.solver.variables.IntVar;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static org.springframework.util.Assert.notNull;


@Component
public class ModelVariablesLocator implements Locator<IntVar, ModelVariablesDTO, String> {


	@Override
	public Optional<IntVar> locate(final List<ModelVariablesDTO> variablesList, final String label) {

		notNull(variablesList, "The list of variables cannot be null.");

		notNull(label, "The label cannot be null.");


		return variablesList.stream()
								.filter(dto -> nonNull(dto.getVariablesByLabel().get(label)))
								.map(dto -> (IntVar) dto.getVariablesByLabel().get(label))
								.findAny();
	}
}
