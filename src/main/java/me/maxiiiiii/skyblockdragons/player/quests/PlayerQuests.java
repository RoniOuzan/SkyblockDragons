package me.maxiiiiii.skyblockdragons.player.quests;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.quests.mining.MiningQuest;
import me.maxiiiiii.skyblockdragons.player.quests.mining.MiningQuests;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Getter
public class PlayerQuests implements Iterable<Quest> {
    private final PlayerSD player;

    private final MiningQuests miningQuests;

    private final List<Quests<?>> quests;

    public PlayerQuests(PlayerSD player) {
        this.player = player;

        this.miningQuests = new MiningQuests(player);

        this.quests = Collections.singletonList(miningQuests);
    }

    public void startQuest(Quest quest) {
        if (quest instanceof MiningQuest)
            this.miningQuests.startQuest((MiningQuest) quest);
    }

    public Quest getQuest(Class<? extends Quest> questClass) {
        for (Quests<?> quests : quests) {
            return quests.getQuest(questClass);
        }
        return null;
    }

    public boolean isCompleted(Class<? extends Quest> questClass) {
        Quest quest = this.getQuest(questClass);
        if (quest != null) return quest.isCompleted();
        return false;
    }

    public void save() {
        miningQuests.save();
    }

    private List<Quest> toList() {
        return new ArrayList<>(miningQuests.getQuests());
    }

    @Override
    public Iterator<Quest> iterator() {
        return this.toList().iterator();
    }

    @Override
    public void forEach(Consumer<? super Quest> action) {
        Objects.requireNonNull(action);
        for (Quest e : this.toList()) {
            action.accept(e);
        }
    }

    @Override
    public Spliterator<Quest> spliterator() {
        return Spliterators.spliterator(this.toList(), Spliterator.ORDERED);
    }

    public Stream<Quest> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
