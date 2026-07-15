package com.liander

/** Staking reward math, mirroring the on-chain vault I built on Solana and EVM:
  *
  *   reward = stakedAmount * elapsedSeconds * rewardRate / AccPrecision
  *
  * rewardRate is scaled by AccPrecision (1e12); BigInt keeps wei-scale amounts exact.
  */
object StakeMath {

  /** Fixed-point scaling for rewardRate (1e12). */
  val AccPrecision: BigInt = BigInt(1000000000000L)

  def reward(amount: BigInt, elapsedSeconds: Long, rewardRate: BigInt): BigInt = {
    require(amount >= 0, "amount must be >= 0")
    require(elapsedSeconds >= 0, "elapsedSeconds must be >= 0")
    require(rewardRate >= 0, "rewardRate must be >= 0")
    amount * BigInt(elapsedSeconds) * rewardRate / AccPrecision
  }
}
