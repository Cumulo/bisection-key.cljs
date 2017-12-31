
bisection-key
----

> Generate a third string key that is in the middle when `compare`d. For example `"a"` and `"b"` result in `"aT"`.

### Usage

```edn
[cirru/bisection-key "0.1.4"]
```

[![Clojars Project](https://img.shields.io/clojars/v/cirru/bisection-key.svg)](https://clojars.org/cirru/bisection-key)

```clojure
(bisection-key.core/bisect "a" "c") ; "b"
(bisection-key.core/bisect "a" "b") ; "aT"
; make sure "a < b"

bisection-key.core/min-id ; "+"
bisection-key.core/mid-id ; "T"
bisection-key.core/max-id ; "z"

; (def v {"a" 1, "b" 1})
(bisection-key.util/key-before v "b") ; "aT"
(bisection-key.util/key-after v "a") ; "aT"
(bisection-key.util/key-prepend v) ; "G"
(bisection-key.util/key-append v) ; "n"

(bisection-key.util/assoc-after v "a" 2) ; (assoc v "aT" 2)
(bisection-key.util/assoc-before v "b" 2) ; (assoc v "aT" 2)
(bisection-key.util/assoc-prepend v 2) ; (assoc v "G" 2)
(bisection-key.util/assoc-append v 2) ; (assoc v "n" 2)
```

Charset:

```text
+-/0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz
```

### Workflow

Workflow https://github.com/minimal-xyz/minimal-shadow-cljs-nodejs

### License

MIT
