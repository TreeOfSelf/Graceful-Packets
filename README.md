<img width="128" height="128" alt="icon" src="https://github.com/user-attachments/assets/866e47da-cdbc-408e-81c4-fe62f4a10ca8" />

# Graceful Packets

## Description
A Fabric mod that prevents the client from crashing when it fails to decode a packet (common with ViaVersion bugs) from the server or encounters registry errors. 
Instead of crashing the entire client when a packet cannot be decoded, the mod simply ignores the problematic packet after logging the error to console, and continues running.
You can optionally also log it to chat, and change settings through the Mod Menu.

## Configuration

Under `./config/graceful-packets.conf`

```
suppress_errors=true
log_to_chat=false
```
* suppress_errors: When set to `true` (default), the mod will suppress packet decode and registry errors instead of crashing. Set to `false` to disable the mod.
* log_to_chat: When set to `true`, suppressed errors will also be displayed in the in-game chat. Default is `false`.


## Support
[Support discord here!](https://discord.gg/3tP3Tqu983)

## License
[CC0](https://creativecommons.org/public-domain/cc0/)
