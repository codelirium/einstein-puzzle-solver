package io.codelirium.constraints.einstein.variable.parser;

import io.codelirium.constraints.einstein.dto.ModelVariablesDTO;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;
import org.springframework.stereotype.Component;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.google.common.collect.Lists.newLinkedList;
import static com.google.common.collect.Maps.newHashMap;
import static io.codelirium.constraints.einstein.entity.EnumEntities.reflect;
import static java.util.Arrays.asList;
import static java.util.Collections.shuffle;
import static org.springframework.util.Assert.notNull;


@Component
public class ModelVariablesParser implements Parser<ModelVariablesDTO, Model> {


	@Override
	public final List<ModelVariablesDTO> parse(final Model model, final Stream<Class<? extends Enum<?>>> enumClazzesStream) {

		notNull(model, "The model cannot be null.");

		notNull(enumClazzesStream, "The enum classes stream cannot be null.");


		final List<ModelVariablesDTO> modelVariablesDTOs = newLinkedList();

		enumClazzesStream.forEach(enumClazz -> {

			final List<String> labels = reflect(enumClazz);


			shuffle(labels);


			final IntVar[] variablesByIndex = model.intVarArray(enumClazz.getSimpleName(), labels.size(), 0, labels.size() - 1);

			final Map<String, IntVar> variablesByLabel = newHashMap();


			final Iterator<IntVar> variable = asList(variablesByIndex).iterator();

			labels.forEach(label -> variablesByLabel.put(label, variable.next()));

			modelVariablesDTOs.add(new ModelVariablesDTO(variablesByIndex, variablesByLabel));
		});


		return modelVariablesDTOs;
	}
}
