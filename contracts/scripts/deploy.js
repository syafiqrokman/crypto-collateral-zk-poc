const hre = require("hardhat");

async function main() {
  const [deployer] = await hre.ethers.getSigners();
  console.log("🚀 Deploying with:", deployer.address);

  const SettlementLogger = await hre.ethers.getContractFactory("SettlementLogger", deployer);
  const contract = await SettlementLogger.deploy();
  await contract.waitForDeployment();

  const address = await contract.getAddress();
  console.log("✅ SettlementLogger deployed to:", address);
}

main().catch((error) => {
  console.error(error);
  process.exitCode = 1;
});
