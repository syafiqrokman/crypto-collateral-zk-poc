# ZK Hash Circuit Build Script

This script automates the full zk-SNARK proof generation lifecycle using Circom and snarkjs.

## Prerequisites

- Node.js & `snarkjs` (installed globally)
- `circom` (compiled locally via Rust)
- Directory structure:
  ```
  zk/
  ├── build/
  ├── circuits/
  │   └── hash.circom
  ├── inputs/
  │   └── input.json
  └── setup/
      └── powersoftau/
  ```

## Usage

Run from the root `zk/` folder:

```bash
chmod +x scripts/build-circuit.sh
./scripts/build-circuit.sh
```

## Output

- `build/hash.r1cs`: Constraint system
- `build/witness.wtns`: Witness for the input
- `build/hash_final.zkey`: Final proving key
- `build/public.json`: Public inputs
- `build/proof.json`: zk-SNARK proof
- `contracts/Verifier.sol`: Solidity contract
