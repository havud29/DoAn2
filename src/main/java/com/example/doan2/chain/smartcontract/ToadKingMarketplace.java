package com.example.doan2.chain.smartcontract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.2.
 */
@SuppressWarnings("rawtypes")
public class ToadKingMarketplace extends Contract {
    public static final String BINARY = "6080604052600160065560646007553480156200001b57600080fd5b5060405162002d4c38038062002d4c83398181016040528101906200004191906200022b565b6001600081905550620000696200005d620000f360201b60201c565b620000fb60201b60201c565b81600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505062000272565b600033905090565b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905081600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a35050565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000620001f382620001c6565b9050919050565b6200020581620001e6565b81146200021157600080fd5b50565b6000815190506200022581620001fa565b92915050565b60008060408385031215620002455762000244620001c1565b5b6000620002558582860162000214565b9250506020620002688582860162000214565b9150509250929050565b612aca80620002826000396000f3fe6080604052600436106100c25760003560e01c8063601fccd01161007f578063c7be7a4911610059578063c7be7a4914610223578063d34c127e14610260578063f2fde38b1461029d578063f8a8fd6d146102c6576100c2565b8063601fccd0146101b8578063715018a6146101e15780638da5cb5b146101f8576100c2565b8063243adbdd146100c7578063443c2fd7146100e3578063490624931461010e5780634cb558761461013957806359f48271146101645780635dc3ffa81461018d575b600080fd5b6100e160048036038101906100dc9190611cf1565b6102dd565b005b3480156100ef57600080fd5b506100f8610a53565b6040516101059190611eb3565b60405180910390f35b34801561011a57600080fd5b50610123610c71565b6040516101309190611f34565b60405180910390f35b34801561014557600080fd5b5061014e610c97565b60405161015b9190611eb3565b60405180910390f35b34801561017057600080fd5b5061018b60048036038101906101869190611cf1565b610ff0565b005b34801561019957600080fd5b506101a26111e1565b6040516101af9190611f70565b60405180910390f35b3480156101c457600080fd5b506101df60048036038101906101da9190611f8b565b611207565b005b3480156101ed57600080fd5b506101f6611575565b005b34801561020457600080fd5b5061020d611589565b60405161021a9190611fff565b60405180910390f35b34801561022f57600080fd5b5061024a60048036038101906102459190611cf1565b6115b3565b6040516102579190612095565b60405180910390f35b34801561026c57600080fd5b5061028760048036038101906102829190611cf1565b611756565b6040516102949190611eb3565b60405180910390f35b3480156102a957600080fd5b506102c460048036038101906102bf91906120dc565b611a1b565b005b3480156102d257600080fd5b506102db611a9e565b005b8060008111610321576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161031890612166565b60405180910390fd5b61032b6004611ae2565b81111561036d576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610364906121d2565b60405180910390fd5b6000600860008481526020019081526020016000209050600115158160040160149054906101000a900460ff161515146103dc576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016103d39061223e565b60405180910390fd5b3373ffffffffffffffffffffffffffffffffffffffff168160030160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff160361046e576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610465906122aa565b60405180910390fd5b60008160020154905060006007546006548361048a91906122f9565b6104949190612382565b90506000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166370a08231336040518263ffffffff1660e01b81526004016104f39190611fff565b602060405180830381865afa158015610510573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061053491906123c8565b905082811015610579576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161057090612467565b60405180910390fd5b6000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663dd62ed3e33306040518363ffffffff1660e01b81526004016105d8929190612487565b602060405180830381865afa1580156105f5573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061061991906123c8565b90508381101561065e576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161065590612522565b60405180910390fd5b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166323b872dd3330876040518463ffffffff1660e01b81526004016106bd93929190612551565b6020604051808303816000875af11580156106dc573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061070091906125b4565b50600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a9059cbb8660030160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16858761077091906125e1565b6040518363ffffffff1660e01b815260040161078d929190612636565b6020604051808303816000875af11580156107ac573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906107d091906125b4565b50600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a9059cbb610817611589565b856040518363ffffffff1660e01b815260040161083592919061265f565b6020604051808303816000875af1158015610854573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061087891906125b4565b50338560040160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506108c66005611af0565b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166323b872dd303388600101546040518463ffffffff1660e01b815260040161092993929190612551565b600060405180830381600087803b15801561094357600080fd5b505af1158015610957573d6000803e3d6000fd5b5050505060008560040160146101000a81548160ff021916908315150217905550338560040160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055507ff198f3fc4cf3c5300e58bde8cf782caf44a5af3b7c522b38ca5201a55cfb9ba6856000015486600101548760030160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168860040160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1688604051610a42959493929190612688565b60405180910390a150505050505050565b60606000610a616004611ae2565b90506000610a6f6005611ae2565b82610a7a91906125e1565b905060008167ffffffffffffffff811115610a9857610a976126db565b5b604051908082528060200260200182016040528015610ad157816020015b610abe611c52565b815260200190600190039081610ab65790505b5090506000805b612710811015610c665760086000600183610af3919061270a565b815260200190815260200160002060040160149054906101000a900460ff1615610c535760086000600183610b28919061270a565b81526020019081526020016000206040518060c00160405290816000820154815260200160018201548152602001600282015481526020016003820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016004820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016004820160149054906101000a900460ff161515151581525050838381518110610c3957610c38612760565b5b60200260200101819052508180610c4f9061278f565b9250505b8080610c5e9061278f565b915050610ad8565b508194505050505090565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60606000610ca56004611ae2565b90506000805b82811015610d83573373ffffffffffffffffffffffffffffffffffffffff1660086000600184610cdb919061270a565b815260200190815260200160002060030160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16148015610d5c575060086000600183610d3c919061270a565b815260200190815260200160002060040160149054906101000a900460ff165b15610d70578180610d6c9061278f565b9250505b8080610d7b9061278f565b915050610cab565b5060008167ffffffffffffffff811115610da057610d9f6126db565b5b604051908082528060200260200182016040528015610dd957816020015b610dc6611c52565b815260200190600190039081610dbe5790505b5090506000805b84811015610fe5573373ffffffffffffffffffffffffffffffffffffffff1660086000600184610e10919061270a565b815260200190815260200160002060030160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16148015610e91575060086000600183610e71919061270a565b815260200190815260200160002060040160149054906101000a900460ff165b15610fd25760086000600183610ea7919061270a565b81526020019081526020016000206040518060c00160405290816000820154815260200160018201548152602001600282015481526020016003820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016004820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016004820160149054906101000a900460ff161515151581525050838381518110610fb857610fb7612760565b5b60200260200101819052508180610fce9061278f565b9250505b8080610fdd9061278f565b915050610de0565b508194505050505090565b8060008111611034576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161102b90612166565b60405180910390fd5b61103e6004611ae2565b811115611080576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401611077906121d2565b60405180910390fd5b600060086000848152602001908152602001600020905060008160040160146101000a81548160ff021916908315150217905550600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166323b872dd303384600101546040518463ffffffff1660e01b815260040161111793929190612551565b600060405180830381600087803b15801561113157600080fd5b505af1158015611145573d6000803e3d6000fd5b505050507f39f8abe555a612ad2c831a5cf89e38bb205a6cec70f983e528c69734a4d017a0816000015482600101548360030160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168460040160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1685600201546040516111d4959493929190612688565b60405180910390a1505050565b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b823373ffffffffffffffffffffffffffffffffffffffff16600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16636352211e836040518263ffffffff1660e01b815260040161127a91906127d7565b602060405180830381865afa158015611297573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906112bb9190612807565b73ffffffffffffffffffffffffffffffffffffffff1614611311576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161130890612880565b60405180910390fd5b8260008111611355576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161134c906128ec565b60405180910390fd5b61135f6004611af0565b6040518060c001604052808481526020018681526020018581526020013373ffffffffffffffffffffffffffffffffffffffff168152602001600073ffffffffffffffffffffffffffffffffffffffff168152602001600115158152506008600085815260200190815260200160002060008201518160000155602082015181600101556040820151816002015560608201518160030160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060808201518160040160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060a08201518160040160146101000a81548160ff021916908315150217905550905050600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166323b872dd3330886040518463ffffffff1660e01b81526004016114fd93929190612551565b600060405180830381600087803b15801561151757600080fd5b505af115801561152b573d6000803e3d6000fd5b505050507fb7c1e5fc292762b6879414351a4c0e1bb8d1633c5743c6d632ec5275943f59c6838633308860405161156695949392919061290c565b60405180910390a15050505050565b61157d611b06565b6115876000611b84565b565b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b6115bb611c52565b81600081116115ff576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016115f690612166565b60405180910390fd5b6116096004611ae2565b81111561164b576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401611642906121d2565b60405180910390fd5b600860008481526020019081526020016000206040518060c00160405290816000820154815260200160018201548152602001600282015481526020016003820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016004820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016004820160149054906101000a900460ff161515151581525050915050919050565b606060006117646004611ae2565b90506000805b828110156117f7578460086000600184611784919061270a565b8152602001908152602001600020600101541480156117d05750600860006001836117af919061270a565b815260200190815260200160002060040160149054906101000a900460ff16155b156117e45781806117e09061278f565b9250505b80806117ef9061278f565b91505061176a565b5060008167ffffffffffffffff811115611814576118136126db565b5b60405190808252806020026020018201604052801561184d57816020015b61183a611c52565b8152602001906001900390816118325790505b5090506000805b84811015611a0e57866008600060018461186e919061270a565b8152602001908152602001600020600101541480156118ba575060086000600183611899919061270a565b815260200190815260200160002060040160149054906101000a900460ff16155b156119fb57600860006001836118d0919061270a565b81526020019081526020016000206040518060c00160405290816000820154815260200160018201548152602001600282015481526020016003820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016004820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016004820160149054906101000a900460ff1615151515815250508383815181106119e1576119e0612760565b5b602002602001018190525081806119f79061278f565b9250505b8080611a069061278f565b915050611854565b5081945050505050919050565b611a23611b06565b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1603611a92576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401611a89906129d1565b60405180910390fd5b611a9b81611b84565b50565b7f59c618f7b9931e72dbed2c94e4630716a0127b95d1c4edc81210be6092633f3733611ac8611589565b30604051611ad8939291906129f1565b60405180910390a1565b600081600001549050919050565b6001816000016000828254019250508190555050565b611b0e611c4a565b73ffffffffffffffffffffffffffffffffffffffff16611b2c611589565b73ffffffffffffffffffffffffffffffffffffffff1614611b82576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401611b7990612a74565b60405180910390fd5b565b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905081600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a35050565b600033905090565b6040518060c00160405280600081526020016000815260200160008152602001600073ffffffffffffffffffffffffffffffffffffffff168152602001600073ffffffffffffffffffffffffffffffffffffffff1681526020016000151581525090565b600080fd5b6000819050919050565b611cce81611cbb565b8114611cd957600080fd5b50565b600081359050611ceb81611cc5565b92915050565b600060208284031215611d0757611d06611cb6565b5b6000611d1584828501611cdc565b91505092915050565b600081519050919050565b600082825260208201905092915050565b6000819050602082019050919050565b611d5381611cbb565b82525050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000611d8482611d59565b9050919050565b611d9481611d79565b82525050565b60008115159050919050565b611daf81611d9a565b82525050565b60c082016000820151611dcb6000850182611d4a565b506020820151611dde6020850182611d4a565b506040820151611df16040850182611d4a565b506060820151611e046060850182611d8b565b506080820151611e176080850182611d8b565b5060a0820151611e2a60a0850182611da6565b50505050565b6000611e3c8383611db5565b60c08301905092915050565b6000602082019050919050565b6000611e6082611d1e565b611e6a8185611d29565b9350611e7583611d3a565b8060005b83811015611ea6578151611e8d8882611e30565b9750611e9883611e48565b925050600181019050611e79565b5085935050505092915050565b60006020820190508181036000830152611ecd8184611e55565b905092915050565b6000819050919050565b6000611efa611ef5611ef084611d59565b611ed5565b611d59565b9050919050565b6000611f0c82611edf565b9050919050565b6000611f1e82611f01565b9050919050565b611f2e81611f13565b82525050565b6000602082019050611f496000830184611f25565b92915050565b6000611f5a82611f01565b9050919050565b611f6a81611f4f565b82525050565b6000602082019050611f856000830184611f61565b92915050565b600080600060608486031215611fa457611fa3611cb6565b5b6000611fb286828701611cdc565b9350506020611fc386828701611cdc565b9250506040611fd486828701611cdc565b9150509250925092565b6000611fe982611d59565b9050919050565b611ff981611fde565b82525050565b60006020820190506120146000830184611ff0565b92915050565b60c0820160008201516120306000850182611d4a565b5060208201516120436020850182611d4a565b5060408201516120566040850182611d4a565b5060608201516120696060850182611d8b565b50608082015161207c6080850182611d8b565b5060a082015161208f60a0850182611da6565b50505050565b600060c0820190506120aa600083018461201a565b92915050565b6120b981611fde565b81146120c457600080fd5b50565b6000813590506120d6816120b0565b92915050565b6000602082840312156120f2576120f1611cb6565b5b6000612100848285016120c7565b91505092915050565b600082825260208201905092915050565b7f4d61726b65744974656d49642073686f756c64206265203e2030000000000000600082015250565b6000612150601a83612109565b915061215b8261211a565b602082019050919050565b6000602082019050818103600083015261217f81612143565b9050919050565b7f4d61726b65744974656d49642073686f756c6420626520657869737400000000600082015250565b60006121bc601c83612109565b91506121c782612186565b602082019050919050565b600060208201905081810360008301526121eb816121af565b9050919050565b7f4e4654206973206e6f742053656c6c696e670000000000000000000000000000600082015250565b6000612228601283612109565b9150612233826121f2565b602082019050919050565b600060208201905081810360008301526122578161221b565b9050919050565b7f596f752063616e206e6f742062757920796f7572206f776e204e465400000000600082015250565b6000612294601c83612109565b915061229f8261225e565b602082019050919050565b600060208201905081810360008301526122c381612287565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b600061230482611cbb565b915061230f83611cbb565b9250817fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff0483118215151615612348576123476122ca565b5b828202905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601260045260246000fd5b600061238d82611cbb565b915061239883611cbb565b9250826123a8576123a7612353565b5b828204905092915050565b6000815190506123c281611cc5565b92915050565b6000602082840312156123de576123dd611cb6565b5b60006123ec848285016123b3565b91505092915050565b7f596f7520646f206e6f74206861766520656e6f75676820544b5420746f20627560008201527f792074686973204e465400000000000000000000000000000000000000000000602082015250565b6000612451602a83612109565b915061245c826123f5565b604082019050919050565b6000602082019050818103600083015261248081612444565b9050919050565b600060408201905061249c6000830185611ff0565b6124a96020830184611ff0565b9392505050565b7f596f7520646f206e6f7420617070726f766520656e6f75676820544b5420746f60008201527f206275792074686973204e465400000000000000000000000000000000000000602082015250565b600061250c602d83612109565b9150612517826124b0565b604082019050919050565b6000602082019050818103600083015261253b816124ff565b9050919050565b61254b81611cbb565b82525050565b60006060820190506125666000830186611ff0565b6125736020830185611ff0565b6125806040830184612542565b949350505050565b61259181611d9a565b811461259c57600080fd5b50565b6000815190506125ae81612588565b92915050565b6000602082840312156125ca576125c9611cb6565b5b60006125d88482850161259f565b91505092915050565b60006125ec82611cbb565b91506125f783611cbb565b92508282101561260a576126096122ca565b5b828203905092915050565b600061262082611f01565b9050919050565b61263081612615565b82525050565b600060408201905061264b6000830185612627565b6126586020830184612542565b9392505050565b60006040820190506126746000830185611ff0565b6126816020830184612542565b9392505050565b600060a08201905061269d6000830188612542565b6126aa6020830187612542565b6126b76040830186612627565b6126c46060830185612627565b6126d16080830184612542565b9695505050505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b600061271582611cbb565b915061272083611cbb565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff03821115612755576127546122ca565b5b828201905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b600061279a82611cbb565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82036127cc576127cb6122ca565b5b600182019050919050565b60006020820190506127ec6000830184612542565b92915050565b600081519050612801816120b0565b92915050565b60006020828403121561281d5761281c611cb6565b5b600061282b848285016127f2565b91505092915050565b7f596f7520617265206e6f74204e46542773206f776e6572000000000000000000600082015250565b600061286a601783612109565b915061287582612834565b602082019050919050565b600060208201905081810360008301526128998161285d565b9050919050565b7f5072696365206d757374203e2030000000000000000000000000000000000000600082015250565b60006128d6600e83612109565b91506128e1826128a0565b602082019050919050565b60006020820190508181036000830152612905816128c9565b9050919050565b600060a0820190506129216000830188612542565b61292e6020830187612542565b61293b6040830186611ff0565b6129486060830185611ff0565b6129556080830184612542565b9695505050505050565b7f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160008201527f6464726573730000000000000000000000000000000000000000000000000000602082015250565b60006129bb602683612109565b91506129c68261295f565b604082019050919050565b600060208201905081810360008301526129ea816129ae565b9050919050565b6000606082019050612a066000830186611ff0565b612a136020830185611ff0565b612a206040830184611ff0565b949350505050565b7f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572600082015250565b6000612a5e602083612109565b9150612a6982612a28565b602082019050919050565b60006020820190508181036000830152612a8d81612a51565b905091905056fea2646970667358221220d3f873d6170828ffc0419f435305e6526c8e1656056c69dffc29d6ae3156279f64736f6c634300080d0033";

    public static final String FUNC_BUYNFT = "buyNft";

    public static final String FUNC_CANCELSELLNFT = "cancelSellNft";

    public static final String FUNC_GETLISTINGNFTS = "getListingNfts";

    public static final String FUNC_GETMARKETITEM = "getMarketItem";

    public static final String FUNC_GETMYLISTINGNFTS = "getMyListingNfts";

    public static final String FUNC_GETNFTSELLHISTORY = "getNftSellHistory";

    public static final String FUNC_LISTNFT = "listNft";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_TEST = "test";

    public static final String FUNC_TOADKINGCOIN = "toadKingCoin";

    public static final String FUNC_TOADKINGNFT = "toadKingNFT";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event LOGGING_EVENT = new Event("Logging", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event TOADNFTLISTED_EVENT = new Event("ToadNFTListed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TOADNFTSALECANCELED_EVENT = new Event("ToadNFTSaleCanceled", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TOADNFTSOLD_EVENT = new Event("ToadNFTSold", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected ToadKingMarketplace(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ToadKingMarketplace(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ToadKingMarketplace(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ToadKingMarketplace(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<LoggingEventResponse> getLoggingEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(LOGGING_EVENT, transactionReceipt);
        ArrayList<LoggingEventResponse> responses = new ArrayList<LoggingEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LoggingEventResponse typedResponse = new LoggingEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.a = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.b = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.c = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LoggingEventResponse> loggingEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, LoggingEventResponse>() {
            @Override
            public LoggingEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LOGGING_EVENT, log);
                LoggingEventResponse typedResponse = new LoggingEventResponse();
                typedResponse.log = log;
                typedResponse.a = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.b = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.c = (String) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<LoggingEventResponse> loggingEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOGGING_EVENT));
        return loggingEventFlowable(filter);
    }

    public static List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public static List<ToadNFTListedEventResponse> getToadNFTListedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TOADNFTLISTED_EVENT, transactionReceipt);
        ArrayList<ToadNFTListedEventResponse> responses = new ArrayList<ToadNFTListedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ToadNFTListedEventResponse typedResponse = new ToadNFTListedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.itemId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.seller = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.buyer = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ToadNFTListedEventResponse> toadNFTListedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ToadNFTListedEventResponse>() {
            @Override
            public ToadNFTListedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TOADNFTLISTED_EVENT, log);
                ToadNFTListedEventResponse typedResponse = new ToadNFTListedEventResponse();
                typedResponse.log = log;
                typedResponse.itemId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.seller = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.buyer = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ToadNFTListedEventResponse> toadNFTListedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TOADNFTLISTED_EVENT));
        return toadNFTListedEventFlowable(filter);
    }

    public static List<ToadNFTSaleCanceledEventResponse> getToadNFTSaleCanceledEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TOADNFTSALECANCELED_EVENT, transactionReceipt);
        ArrayList<ToadNFTSaleCanceledEventResponse> responses = new ArrayList<ToadNFTSaleCanceledEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ToadNFTSaleCanceledEventResponse typedResponse = new ToadNFTSaleCanceledEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.itemId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.seller = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.buyer = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ToadNFTSaleCanceledEventResponse> toadNFTSaleCanceledEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ToadNFTSaleCanceledEventResponse>() {
            @Override
            public ToadNFTSaleCanceledEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TOADNFTSALECANCELED_EVENT, log);
                ToadNFTSaleCanceledEventResponse typedResponse = new ToadNFTSaleCanceledEventResponse();
                typedResponse.log = log;
                typedResponse.itemId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.seller = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.buyer = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ToadNFTSaleCanceledEventResponse> toadNFTSaleCanceledEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TOADNFTSALECANCELED_EVENT));
        return toadNFTSaleCanceledEventFlowable(filter);
    }

    public static List<ToadNFTSoldEventResponse> getToadNFTSoldEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TOADNFTSOLD_EVENT, transactionReceipt);
        ArrayList<ToadNFTSoldEventResponse> responses = new ArrayList<ToadNFTSoldEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ToadNFTSoldEventResponse typedResponse = new ToadNFTSoldEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.itemId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.seller = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.buyer = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ToadNFTSoldEventResponse> toadNFTSoldEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ToadNFTSoldEventResponse>() {
            @Override
            public ToadNFTSoldEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TOADNFTSOLD_EVENT, log);
                ToadNFTSoldEventResponse typedResponse = new ToadNFTSoldEventResponse();
                typedResponse.log = log;
                typedResponse.itemId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.seller = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.buyer = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ToadNFTSoldEventResponse> toadNFTSoldEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TOADNFTSOLD_EVENT));
        return toadNFTSoldEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> buyNft(BigInteger _marketItemId, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BUYNFT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_marketItemId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> cancelSellNft(BigInteger _marketItemId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CANCELSELLNFT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_marketItemId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getListingNfts() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETLISTINGNFTS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<ToadNFTMarket>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<ToadNFTMarket> getMarketItem(BigInteger _marketItemId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETMARKETITEM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_marketItemId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<ToadNFTMarket>() {}));
        return executeRemoteCallSingleValueReturn(function, ToadNFTMarket.class);
    }

    public RemoteFunctionCall<List> getMyListingNfts() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETMYLISTINGNFTS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<ToadNFTMarket>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<List> getNftSellHistory(BigInteger _tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETNFTSELLHISTORY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<ToadNFTMarket>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> listNft(BigInteger _tokenId, BigInteger _price, BigInteger _itemId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_LISTNFT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_tokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.generated.Uint256(_itemId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> test() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TEST, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> toadKingCoin() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOADKINGCOIN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> toadKingNFT() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOADKINGNFT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ToadKingMarketplace load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ToadKingMarketplace(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ToadKingMarketplace load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ToadKingMarketplace(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ToadKingMarketplace load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ToadKingMarketplace(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ToadKingMarketplace load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ToadKingMarketplace(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ToadKingMarketplace> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _toadKingTokenContract, String _toadKingNFTContract) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _toadKingTokenContract), 
                new org.web3j.abi.datatypes.Address(160, _toadKingNFTContract)));
        return deployRemoteCall(ToadKingMarketplace.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<ToadKingMarketplace> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _toadKingTokenContract, String _toadKingNFTContract) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _toadKingTokenContract), 
                new org.web3j.abi.datatypes.Address(160, _toadKingNFTContract)));
        return deployRemoteCall(ToadKingMarketplace.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ToadKingMarketplace> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _toadKingTokenContract, String _toadKingNFTContract) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _toadKingTokenContract), 
                new org.web3j.abi.datatypes.Address(160, _toadKingNFTContract)));
        return deployRemoteCall(ToadKingMarketplace.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ToadKingMarketplace> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _toadKingTokenContract, String _toadKingNFTContract) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _toadKingTokenContract), 
                new org.web3j.abi.datatypes.Address(160, _toadKingNFTContract)));
        return deployRemoteCall(ToadKingMarketplace.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class ToadNFTMarket extends StaticStruct {
        public BigInteger itemId;

        public BigInteger tokenId;

        public BigInteger price;

        public String seller;

        public String buyer;

        public Boolean isSelling;

        public ToadNFTMarket(BigInteger itemId, BigInteger tokenId, BigInteger price, String seller, String buyer, Boolean isSelling) {
            super(new org.web3j.abi.datatypes.generated.Uint256(itemId), 
                    new org.web3j.abi.datatypes.generated.Uint256(tokenId), 
                    new org.web3j.abi.datatypes.generated.Uint256(price), 
                    new org.web3j.abi.datatypes.Address(160, seller), 
                    new org.web3j.abi.datatypes.Address(160, buyer), 
                    new org.web3j.abi.datatypes.Bool(isSelling));
            this.itemId = itemId;
            this.tokenId = tokenId;
            this.price = price;
            this.seller = seller;
            this.buyer = buyer;
            this.isSelling = isSelling;
        }

        public ToadNFTMarket(Uint256 itemId, Uint256 tokenId, Uint256 price, Address seller, Address buyer, Bool isSelling) {
            super(itemId, tokenId, price, seller, buyer, isSelling);
            this.itemId = itemId.getValue();
            this.tokenId = tokenId.getValue();
            this.price = price.getValue();
            this.seller = seller.getValue();
            this.buyer = buyer.getValue();
            this.isSelling = isSelling.getValue();
        }
    }

    public static class LoggingEventResponse extends BaseEventResponse {
        public String a;

        public String b;

        public String c;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class ToadNFTListedEventResponse extends BaseEventResponse {
        public BigInteger itemId;

        public BigInteger tokenId;

        public String seller;

        public String buyer;

        public BigInteger price;
    }

    public static class ToadNFTSaleCanceledEventResponse extends BaseEventResponse {
        public BigInteger itemId;

        public BigInteger tokenId;

        public String seller;

        public String buyer;

        public BigInteger price;
    }

    public static class ToadNFTSoldEventResponse extends BaseEventResponse {
        public BigInteger itemId;

        public BigInteger tokenId;

        public String seller;

        public String buyer;

        public BigInteger price;
    }
}