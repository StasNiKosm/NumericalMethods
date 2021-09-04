package gauss.util;

import gauss.expressions.Expression;
import gauss.expressions.ExpressionImpl;
import gauss.linearsystems.LinearSystem;
import gauss.linearsystems.SystemOfExpressions;

import java.util.ArrayList;
import java.util.List;

public final class CopyLinearSystemUtil {
    public static SystemOfExpressions<Double, ExpressionImpl> copyLS( final SystemOfExpressions<Double, ExpressionImpl> sle){
        int n = sle.size();
        List<ExpressionImpl> expressions = new ArrayList<>(n);
        for(int i = 0; i < n; i++) {
            List<Double> coef = new ArrayList<>(n+1);
            for (int j = 0; j < n+1; j++) {
                coef.add(sle.getElement(i, j));
            }
            expressions.add(new ExpressionImpl(coef));
        }
        return new LinearSystem<>(expressions);
    }
}
