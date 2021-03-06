package com.codehub.spring.eshop.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

/**
 * Created by Dimitris on 14/5/2018.
 */

@Entity
@Table(name = "access_tokens")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessToken {

    @Id
    @Column(name = "access_token_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "access_token", nullable = false)
    private UUID accessToken;

    @Column(name = "created_on", nullable = false)
    private Instant createdOn;

    @Column(name = "expires_in", nullable = false)
    private Instant expiresIn;
}
