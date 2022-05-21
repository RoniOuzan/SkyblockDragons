package me.maxiiiiii.skyblockdragons.item.material.interfaces;

import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement;

import java.util.List;

public interface ItemRequirementAble extends ItemAble {
    List<Requirement> getRequirements();
}
