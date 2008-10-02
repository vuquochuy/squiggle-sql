package com.truemesh.squiggle.tests;

import com.truemesh.squiggle.*;
import com.truemesh.squiggle.criteria.BetweenCriteria;

import org.junit.Test;
import static org.junit.Assert.assertThat;

public class Test010FunctionsAndUsingColumnsWithBetweenOperator {
    @Test
    public void functionsAndUsingColumnsWithBetweenOperator() {
        Table cards = new Table("credit_cards");

        SelectQuery select = new SelectQuery(cards);

        select.addToSelection(cards.getColumn("number"));
        select.addToSelection(cards.getColumn("issue"));

        select.addCriteria(
                new BetweenCriteria(new FunctionCall("getDate"),
                        cards.getColumn("issue_date"), cards.getColumn("expiry_date")));
        
        assertThat(select, SqlMatcher.generatesSql(
                "SELECT " +
                "    credit_cards.number , " +
                "    credit_cards.issue " +
                "FROM " +
                "    credit_cards " +
                "WHERE " +
                "    getDate() BETWEEN credit_cards.issue_date AND credit_cards.expiry_date"));
    }
}