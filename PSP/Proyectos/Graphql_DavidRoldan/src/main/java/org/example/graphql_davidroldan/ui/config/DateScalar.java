package org.example.graphql_davidroldan.ui.config;

import graphql.language.StringValue;
import graphql.schema.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateScalar {
    public static final GraphQLScalarType DATE = GraphQLScalarType.newScalar()
            .name("Date")
            .description("A custom scalar that handles date values")
            .coercing(new Coercing() {
                @Override
                public Object serialize(Object dataFetcherResult) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    return formatter.format((Date) dataFetcherResult);
                }

                @Override
                public Object parseValue(Object input) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        return formatter.parse((String) input);
                    } catch (ParseException e) {
                        throw new RuntimeException("Unable to parse date", e);
                    }
                }

                @Override
                public Object parseLiteral(Object input) {
                    if (!(input instanceof StringValue)) return null;
                    return parseValue(((StringValue) input).getValue());
                }
            }).build();
}
