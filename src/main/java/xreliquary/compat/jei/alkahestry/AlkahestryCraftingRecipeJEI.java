package xreliquary.compat.jei.alkahestry;

import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

class AlkahestryCraftingRecipeJEI extends BlankRecipeWrapper {
	@Nonnull
	private final List<Object> inputs;

	@Nonnull
	private final List<Object> outputs;

	@SuppressWarnings("unchecked")
	public AlkahestryCraftingRecipeJEI(@Nonnull Object input, @Nonnull ItemStack tomeInput, @Nonnull Object output, @Nonnull ItemStack tomeOutput) {
		this.inputs = Arrays.asList(input, tomeInput);
		this.outputs = Arrays.asList(output, tomeOutput);
	}

	@Override
	public List getInputs() {
		return inputs;
	}

	@Override
	public List getOutputs() {
		return outputs;
	}
}
