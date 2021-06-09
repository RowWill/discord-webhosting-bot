# discord-webhosting-bot

A bot for managing hosting instances.

# Endpoints available:

# /alive GET
  # Returns 
    healthcheck
##
##
# /generate_test_token GET
  # Required parameters:
   - password | String | The development or production password to generate a key
                         Default dev password: password123
   - issuedBy | String | Arbituary string containing the issuer
   - issuedTo | String | Arbituary string containing the issuee
   - tokenType | enum | DEVELOPMENT or PRODUCTION
  # Optional parameters
   - description | String | a decription for the token
   # Returns 
    An Api Token
##
##
# api/test-api GET
  # Required parameters
   - token | String | the api token
   # Returns 
    JSON detailing available node(s) and environment(s) with their relevant config 
