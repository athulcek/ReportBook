#R8 / ProGuard
#Moshi contains minimally required rules for its own internals to work without need
#for consumers to embed their own. However if you are using reflective serialization
#and R8 or ProGuard, you must add keep rules in your proguard configuration file for
#your reflectively serialized classes.
#
#Enums
#Annotate enums with @JsonClass(generateAdapter = false) to prevent them from being removed/obfuscated from your code by R8/ProGuard.