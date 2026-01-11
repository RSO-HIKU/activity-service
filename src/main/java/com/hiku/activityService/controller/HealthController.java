package com.hiku.activityService.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.json.Json;
import javax.json.JsonObject;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.time.Instant;

/**
 * Health check endpoints for Kubernetes probes.
 * 
 * Endpoints:
 * - /api/activities/health        - Overall health status
 * - /api/activities/health/live   - Liveness probe (is the service running?)
 * - /api/activities/health/ready  - Readiness probe (is the service ready to receive traffic?)
 */
@Path("/health")
@ApplicationScoped
public class HealthController {

    /**
     * Overall health check - returns basic service status.
     * Used for monitoring dashboards and debugging.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response health() {
        JsonObject healthResponse = Json.createObjectBuilder()
                .add("status", "UP")
                .add("service", "activity-service")
                .add("timestamp", Instant.now().toString())
                .build();

        return Response.ok(healthResponse.toString()).build();
    }

    /**
     * Liveness probe endpoint.
     * Returns UP if the service is running (JVM is alive).
     * Kubernetes uses this to decide if the container should be restarted.
     */
    @GET
    @Path("/live")
    @Produces(MediaType.APPLICATION_JSON)
    public Response liveness() {
        RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
        long uptime = runtimeBean.getUptime();

        JsonObject response = Json.createObjectBuilder()
                .add("status", "UP")
                .add("service", "activity-service")
                .add("timestamp", Instant.now().toString())
                .add("uptime_ms", uptime)
                .build();

        return Response.ok(response.toString()).build();
    }

    /**
     * Readiness probe endpoint.
     * Returns UP if the service is ready to accept traffic.
     * Kubernetes uses this to decide if traffic should be routed to this pod.
     */
    @GET
    @Path("/ready")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readiness() {
        JsonObject response = Json.createObjectBuilder()
                .add("status", "UP")
                .add("service", "activity-service")
                .add("timestamp", Instant.now().toString())
                .build();

        return Response.ok(response.toString()).build();
    }
}
