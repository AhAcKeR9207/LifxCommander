// package control.lifx.Objects;
//
// import control.lifx.Constants;
// import control.lifx.Dynamic;
//
// public class Aliasable {
//     // This class has no real purpose, it just allows me to return a Light, Pattern, PatternGroup, or Sequence in the findAliasEntity function.
//     // It also is the best place for the aforementioned function for now.
//
//     public Aliasable findAliasIdentity(String alias) {
//         String key = Dynamic.aliasDict.get(alias);
//
//         // If the key is an alias, then run this function with the key as input.
//         // Be ready to handle StackOverflowExceptions
//         if (Dynamic.aliasDict.containsKey(key)) {
//             findAliasIdentity(key);
//         } else if (Dynamic.lightDict.containsKey(key)) {
//             return Dynamic.lightDict.get(key);
//         } else if (Dynamic.patternDict.containsKey(key)) {
//             return Dynamic.patternDict.get(key);
//         } else if (Dynamic.patternGroupDict.containsKey(key)) {
//             return Dynamic.patternGroupDict.get(key);
//         } else if (Dynamic.sequenceDict.containsKey(key)) {
//             return Dynamic.sequenceDict.get(key);
//         } else if (Constants.commandDict.contains(key)) {
//             return Constants.commandDict.get(Constants.commandDict.indexOf(key));
//         } else {
//             // Throw a "AliasNotFound" exception.
//         }
//
//         return null;
//     }
// }