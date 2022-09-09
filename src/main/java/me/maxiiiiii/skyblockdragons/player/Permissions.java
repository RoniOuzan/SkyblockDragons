package me.maxiiiiii.skyblockdragons.player;

import org.bukkit.permissions.Permission;

public class Permissions {
    public static class Party {
        public static final Permission FORCE_JOIN = new Permission("skyblockdragons.party.forcejoin");
        public static final Permission SILENT_JOIN = new Permission("skyblockdragons.party.silentjoin");
        public static final Permission ADMIN_LIST = new Permission("skyblockdragons.party.adminlist");
        public static final Permission SEE_PARTIES = new Permission("skyblockdragons.party.seeparties");
    }
}
