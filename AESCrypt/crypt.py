#!/usr/bin/python3

import base64
from Crypto.Cipher import AES
from Crypto.Util.Padding import pad, unpad

#message to be encrypted in string
message = "thisissecretmessage"
#convert message to bytes
msgb = message.encode()


##Encryption
#set parameters
#len(key) and len(iv) must be 16bytes
ivs = "bJQSr2jE8253Plt+IdbeFA=="    #in string
iv = base64.b64decode(ivs)          #in bytes
keys = "secretkey16bytes"           #in string
key = keys.encode()                 #in bytes

#create cipher object
cipher = AES.new(key, AES.MODE_CBC, iv)
#encrypt message using the cipher object
ct_bytes = cipher.encrypt(pad(msgb, AES.block_size))
ct = base64.b64encode(ct_bytes).decode()    #convert ciphertext to readable format: base64 encoding and converting to string
print("cipher text is: " + ct)
print("iv is: " + ivs)
print("key is: " + keys)

##Decryption
#create a new cipher object
cipher2 = AES.new(key, AES.MODE_CBC, iv)
#convert readable ciphertext back to binary format, by base64 decoding 
ct_bytes2 = base64.b64decode(ct)
#decrypt ciphertext
pt = unpad(cipher2.decrypt(ct_bytes2), AES.block_size)

#use comma to concatenate bytes
print("The decrypted message is: ", pt)
