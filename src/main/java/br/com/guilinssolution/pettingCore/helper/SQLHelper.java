package br.com.guilinssolution.pettingCore.helper;

import com.querydsl.core.types.dsl.BooleanExpression;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class SQLHelper {

    public static BooleanExpression addAnd(BooleanExpression root, List<BooleanExpression> booleanExpressions) {
        if(CollectionUtils.isNotEmpty(booleanExpressions)) {
            root = (root != null) ? root : booleanExpressions.get(0);
            for (BooleanExpression booleanExpression : booleanExpressions) {
                root = root.and(booleanExpression);
            }
        }
        return root;
    }

    public static BooleanExpression addAnd(List<BooleanExpression> booleanExpressions) {
        BooleanExpression root = null;
        if(CollectionUtils.isNotEmpty(booleanExpressions)) {
            for (BooleanExpression booleanExpression : booleanExpressions) {
                root = (root == null) ?
                        booleanExpression:
                        root.and(booleanExpression);
            }
        }
        return root;
    }

}
