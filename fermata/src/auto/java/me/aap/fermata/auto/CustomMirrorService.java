package me.aap.fermata.auto;

/**
 * Same as {@link MirrorService}, under a distinct class name: Android Auto de-duplicates projection
 * services by class name, so this build's entry would otherwise be hidden by an upstream Fermata
 * Auto installed alongside it.
 */
public class CustomMirrorService extends MirrorService {
}
