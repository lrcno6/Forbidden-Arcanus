package com.stal111.forbidden_arcanus.data.rituals;

import com.stal111.forbidden_arcanus.ForbiddenArcanus;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.EssenceType;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.EssencesDefinition;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.Ritual;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.RitualInput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author stal111
 * @since 2023-02-03
 */
public class RitualBuilder {

    private final Ingredient mainIngredient;
    private final ItemStack result;

    private final List<RitualInput> inputs = new ArrayList<>();
    private final Map<EssenceType, Integer> essences = new HashMap<>();

    public RitualBuilder(ItemStack mainIngredient, ItemStack result) {
        this.mainIngredient = Ingredient.of(mainIngredient);
        this.result = result;
    }

    public RitualBuilder input(Ingredient ingredient) {
        return this.input(ingredient, 1);
    }

    public RitualBuilder input(Ingredient ingredient, int amount) {
        this.inputs.add(new RitualInput(ingredient, amount));

        return this;
    }

    public RitualBuilder aureal(int amount) {
        this.essences.put(EssenceType.AUREAL, amount);

        return this;
    }

    public RitualBuilder souls(int amount) {
        this.essences.put(EssenceType.SOULS, amount);

        return this;
    }

    public RitualBuilder blood(int amount) {
        this.essences.put(EssenceType.BLOOD, amount);

        return this;
    }

    public RitualBuilder experience(int amount) {
        this.essences.put(EssenceType.EXPERIENCE, amount);

        return this;
    }

    public Ritual build() {
        return new Ritual(this.inputs, this.mainIngredient, this.result, EssencesDefinition.of(this.essences), new ResourceLocation(ForbiddenArcanus.MOD_ID, "textures/effect/magic_circle/absolute.png"), new ResourceLocation(ForbiddenArcanus.MOD_ID, "textures/effect/magic_circle/inner_protection.png"));
    }
}
