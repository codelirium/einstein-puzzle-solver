package io.codelirium.constraints.einstein.constraint;

import io.codelirium.constraints.einstein.dto.ModelVariablesDTO;
import org.chocosolver.solver.Model;
import org.springframework.stereotype.Component;
import java.util.List;

import static org.springframework.util.Assert.notNull;


@Component
public class FactEnforcer implements Enforcable<Model, ModelVariablesDTO> {

	@Override
	public void enforce(final Model model, final List<ModelVariablesDTO> variablesList) {

		notNull(model, "The model cannot be null.");

		notNull(variablesList, "The list of variables cannot be null.");


		variablesList.forEach(modelVariables -> model.allDifferent(modelVariables.getVariablesByIndex()).post());
	}
}
