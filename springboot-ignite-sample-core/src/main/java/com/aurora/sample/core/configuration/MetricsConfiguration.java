package com.aurora.sample.core.configuration;

import com.aurora.architecture.metrics.aspect.AbstractMetricsAspect;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cjrequena on 04/09/16.
 */
@Configuration
@ComponentScan(basePackages = {"com.aurora.architecture.metrics"}, basePackageClasses = AbstractMetricsAspect.class)
public class MetricsConfiguration {
}
