#!/bin/bash

set -e

echo "🌀 Step 1: Compile Circom circuit"
circom circuits/proofOfHash.circom --r1cs --wasm --sym -o build

echo "🧠 Step 2: Generate witness"
node build/proofOfHash_js/generate_witness.js build/proofOfHash_js/proofOfHash.wasm inputs/input.json build/witness.wtns

echo "🌪️ Step 3: Powers of Tau Ceremony"
snarkjs powersoftau new bn128 12 setup/powersoftau/pot12_0000.ptau -v
snarkjs powersoftau contribute setup/powersoftau/pot12_0000.ptau setup/powersoftau/pot12_final.ptau --name="Syafiq" -v
snarkjs powersoftau prepare phase2 setup/powersoftau/pot12_final.ptau setup/powersoftau/pot12_final_prepared.ptau

echo "🏗️ Step 4: Setup zkey and verification key"
snarkjs groth16 setup build/proofOfHash.r1cs setup/powersoftau/pot12_final_prepared.ptau build/proofOfHash_0000.zkey
snarkjs zkey contribute build/proofOfHash_0000.zkey build/proofOfHash_final.zkey --name="Syafiq Final" -v
snarkjs zkey export verificationkey build/proofOfHash_final.zkey build/verification_key.json

echo "🔐 Step 5: Generate proof"
snarkjs groth16 prove build/proofOfHash_final.zkey build/witness.wtns build/proof.json build/public.json

echo "✅ Step 6: Verify proof"
snarkjs groth16 verify build/verification_key.json build/public.json build/proof.json

echo "🧾 Step 7: Export Solidity Verifier"
snarkjs zkey export solidityverifier build/proofOfHash_final.zkey contracts/Verifier.sol

echo "🎉 Done!"
