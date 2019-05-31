package com.ShaHar91.poemcollection.injection.qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Because there will be multiple Context 'instances' to inject,
 * we have to create a qualifier so we can determine which is needed.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityContext {
}
