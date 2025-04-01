// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

contract SettlementLogger {
    event SettlementRecorded(address indexed sender, string zkHash);

    function recordSettlement(string calldata zkHash) external {
        emit SettlementRecorded(msg.sender, zkHash);
    }
}
