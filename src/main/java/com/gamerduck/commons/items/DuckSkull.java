package com.gamerduck.commons.items;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.UUID;

/**
 * DuckSkull is meant to be used in place of ItemStack
 * Class will work the same as ItemStack, just adds a few shortcuts to make life easier
 * This class is focused on replacing skulls specifically
 *
 * @author GamerDuck123
 */
public class DuckSkull extends ItemStack {
    private Method metaSetProfileMethod;
    private Field metaProfileField;

    public DuckSkull() {
        super(Material.PLAYER_HEAD, 1);
    }

    /**
     * Modifies a skull to use the skin of the player with a given UUID.
     *
     * @param id The Player's UUID.
     * @return The head of the Player.
     */
    public DuckSkull fromUuid(UUID id) {
        editMeta(SkullMeta.class, meta -> meta.setOwningPlayer(Bukkit.getOfflinePlayer(id)));
//		SkullMeta meta = (SkullMeta) getItemMeta();
//		meta.setOwningPlayer(Bukkit.getOfflinePlayer(id));
//		setItemMeta(meta);
        return this;
    }

    /**
     * Modifies a skull to use the skin of the player with a given name.
     *
     * @param name The Player's name.
     * @return The head of the Player.
     * @deprecated names don't make for good identifiers.
     */
    @Deprecated
    public DuckSkull fromName(String name) {
        editMeta(SkullMeta.class, meta -> meta.setOwningPlayer(Bukkit.getOfflinePlayer(name)));
//		SkullMeta meta = (SkullMeta) getItemMeta();
//		meta.setOwningPlayer(Bukkit.getOfflinePlayer(name));
//		setItemMeta(meta);
        return this;
    }

    /**
     * Modifies a skull to use the skin based on the given base64 string.
     *
     * @param item   The ItemStack to put the base64 onto. Must be a player skull.
     * @param base64 The base64 string containing the texture.
     * @return The head with a custom texture.
     */
    public DuckSkull fromBase64(String base64) {
        editMeta(SkullMeta.class, meta -> mutateItemMeta(meta, base64));
//		if (!(getItemMeta() instanceof SkullMeta)) return this;
//		SkullMeta meta = (SkullMeta) getItemMeta();
//		mutateItemMeta(meta, base64);
//		setItemMeta(meta);
        return this;
    }

    /**
     * Modifies a skull to use the skin at the given Mojang URL.
     *
     * @param url The URL of the Mojang skin.
     * @return The head associated with the URL.
     */
    public ItemStack fromUrl(String url) {
        return fromBase64(urlToBase64(url));
    }


    private String urlToBase64(String url) {
        URI actualUrl;
        try {
            actualUrl = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String toEncode = "{\"textures\":{\"SKIN\":{\"url\":\"" + actualUrl.toString() + "\"}}}";
        return Base64.getEncoder().encodeToString(toEncode.getBytes());
    }

    private GameProfile makeProfile(String b64) {
        // random uuid based on the b64 string
        UUID id = new UUID(
                b64.substring(b64.length() - 20).hashCode(),
                b64.substring(b64.length() - 10).hashCode()
        );
        GameProfile profile = new GameProfile(id, "Player");
        profile.getProperties().put("textures", new Property("textures", b64));
        return profile;
    }

    private void mutateItemMeta(SkullMeta meta, String b64) {
        try {
            if (metaSetProfileMethod == null) {
                metaSetProfileMethod = meta.getClass().getDeclaredMethod("setProfile", GameProfile.class);
                metaSetProfileMethod.setAccessible(true);
            }
            metaSetProfileMethod.invoke(meta, makeProfile(b64));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            // if in an older API where there is no setProfile method,
            // we set the profile field directly.
            try {
                if (metaProfileField == null) {
                    metaProfileField = meta.getClass().getDeclaredField("profile");
                    metaProfileField.setAccessible(true);
                }
                metaProfileField.set(meta, makeProfile(b64));

            } catch (NoSuchFieldException | IllegalAccessException ex2) {
                ex2.printStackTrace();
            }
        }
    }
}
