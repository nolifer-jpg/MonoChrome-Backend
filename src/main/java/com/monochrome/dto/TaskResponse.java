package com.monochrome.dto;

import java.time.Instant;

public record TaskResponse(
    Long id,
    String description,
    boolean completed,
    Instant createdAt
) {}
