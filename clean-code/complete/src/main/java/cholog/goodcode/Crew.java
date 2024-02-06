package cholog.goodcode;

import java.util.Set;

record Crew(
        String name,
        Set<String> likeMenuItems,
        Set<String> dislikeMenuItems
) {
}
