package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public record TermDto( Long id, String name) implements Serializable { }
