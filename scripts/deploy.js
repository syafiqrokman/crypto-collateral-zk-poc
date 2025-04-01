const hre = require("hardhat");

async function main() {
  const SettlementLogger = await hre.ethers.getContractFactory("SettlementLogger");
  const contract = await SettlementLogger.deploy();
  await contract.waitForDeployment();

  console.log("✅ SettlementLogger deployed at:", await contract.getAddress());
}

main().catch((error) => {
  console.error(error);
  process.exitCode = 1;
});
