package io.codelirium.constraints.einstein.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.chocosolver.solver.variables.IntVar;
import java.io.Serializable;
import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
public class ModelVariablesDTO implements Serializable {

	private static final long serialVersionUID = 8101149479440953972L;


	private IntVar[] variablesByIndex;

	private Map<String, IntVar> variablesByLabel;

}
