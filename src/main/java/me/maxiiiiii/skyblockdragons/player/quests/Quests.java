package me.maxiiiiii.skyblockdragons.player.quests;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.storage.Variables;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Getter
public class Quests<Q extends Quest> implements Iterable<Q> {
    private final PlayerSD player;
    private final Set<Q> quests;

    public Quests(PlayerSD player) {
        this.player = player;
        this.quests = new HashSet<>(Variables.getList(player.getUniqueId(), "Quests", new ArrayList<>()));
    }

    public Set<Q> getCompletedQuests() {
        return this.quests.stream().filter(Quest::isCompleted).collect(Collectors.toSet());
    }

    public Set<Q> getActiveQuests() {
        return this.quests.stream().filter(q -> !q.isCompleted()).collect(Collectors.toSet());
    }

    public void startQuest(Q quest) {
        this.quests.add(quest);
    }

    public Q getQuest(Class<? extends Quest> questClass) {
        for (Q quest : quests) {
            if (quest.getClass().equals(questClass)) {
                return quest;
            }
        }
        return null;
    }

    public void save() {
        Variables.set(this.player.getUniqueId(), "Quests", new ArrayList<>(this.quests));
    }

    @Override
    public Iterator<Q> iterator() {
        return this.quests.iterator();
    }

    @Override
    public void forEach(Consumer<? super Q> action) {
        Objects.requireNonNull(action);
        for (Q e : this.quests) {
            action.accept(e);
        }
    }

    @Override
    public Spliterator<Q> spliterator() {
        return Spliterators.spliterator(this.quests, Spliterator.ORDERED);
    }
}
