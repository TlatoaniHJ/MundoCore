package us.tlatoani.mundocore.base;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

public class CondBoolean extends Condition {
    private Expression<Boolean> cond;
    private boolean negated;

    @Override
    public boolean check(Event event) {
        Boolean bool = cond.getSingle(event);
        return bool != null && bool != negated;
    }

    @Override
    public String toString(Event event, boolean b) {
        return cond.toString();
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        cond = (Expression<Boolean>) expressions[0];
        if (cond.getClass().getSimpleName().equals("ExprJavaCall")) {
            return false;
        }
        negated = parseResult.mark == 1;
        return true;
    }
}
