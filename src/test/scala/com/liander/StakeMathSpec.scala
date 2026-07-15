package com.liander

import org.scalatest.funsuite.AnyFunSuite

class StakeMathSpec extends AnyFunSuite {

  private val e18: BigInt = BigInt(10).pow(18)
  private val rate: BigInt = BigInt(1000000)
  private val amount: BigInt = BigInt(1000) * e18

  test("reward matches the formula") {
    // 1000 tokens, rate 1e6, 100_000s -> 100 tokens (matches the Solidity/Anchor tests)
    assert(StakeMath.reward(amount, 100000, rate) == BigInt(100) * e18)
  }

  test("scales with duration") {
    assert(StakeMath.reward(amount, 150000, rate) == BigInt(150) * e18)
  }

  test("zero when no time elapsed") {
    assert(StakeMath.reward(amount, 0, rate) == BigInt(0))
  }

  test("rejects negative inputs") {
    assertThrows[IllegalArgumentException](StakeMath.reward(BigInt(-1), 100, rate))
    assertThrows[IllegalArgumentException](StakeMath.reward(amount, -1, rate))
  }
}
