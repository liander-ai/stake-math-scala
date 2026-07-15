# Stake Math (Scala)

A small **Scala** library (Maven + `scala-maven-plugin` + **ScalaTest**) implementing the staking reward math from my on-chain vault:

```
reward = stakedAmount * elapsedSeconds * rewardRate / AccPrecision   (AccPrecision = 1e12)
```

It's the Scala version of the same formula I built in Solidity, Rust/Anchor, Java, and Kotlin. Scala's `BigInt` keeps wei-scale amounts exact, and inputs are validated with `require`.

## Build & test

```bash
mvn test
```

The ScalaTest suite confirms the reward matches the reference values (100 tokens over 100,000s, 150 over 150,000s — the same numbers verified in the Solidity and Anchor suites), zero when no time elapses, and rejection of negative inputs. Runs in CI on every push.

## Files

```
src/main/scala/com/liander/StakeMath.scala        the reward calculation
src/test/scala/com/liander/StakeMathSpec.scala     ScalaTest suite
pom.xml                                             Maven + scala-maven-plugin
```

## Stack

- **Scala 2.13**, **Maven**, **ScalaTest**

## License

MIT
