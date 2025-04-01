require("@nomicfoundation/hardhat-toolbox");

module.exports = {
  defaultNetwork: "quorum",
  networks: {
    quorum: {
      url: "http://localhost:8545",
      accounts: [
        "0x8f2a55949038a9610f50fb23b5883af3b4ecb3c3bb792cbcefbd1542c692be63"
      ]
    }
  },
  solidity: {
    compilers: [
      {
        version: "0.8.20"
      },
      {
        version: "0.8.28"
      }
    ]
  },
  paths: {
    sources: "./contracts",
    artifacts: "./zk/artifacts",
    cache: "./zk/cache"
  }
};
