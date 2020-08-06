
bisection-key
----

> Generate a third string key that is in the middle when `compare`d. For example `"a"` and `"b"` result in `"aT"`.

### Usage

```edn
[cirru/bisection-key "0.1.6-a1"]
```

[![Clojars Project](https://img.shields.io/clojars/v/cirru/bisection-key.svg)](https://clojars.org/cirru/bisection-key)

Under `bisection-key.core`:

```clojure
; make sure the first parameter is littler
(bisection-key.core/bisect "a" "c") ; "b"
(bisection-key.core/bisect "a" "b") ; "aT"
(bisection-key.core/bisect "a34fd" "f3554") ; "c"

bisection-key.core/min-id ; "+"
bisection-key.core/mid-id ; "T"
bisection-key.core/max-id ; "z"
```

Functions under `bisection-key.util`:

```clojure
; (def v {"a" 1, "b" 1})
(key-before v "b") ; "aT"
(key-after v "a") ; "aT"
(key-prepend v) ; "G"
(key-append v) ; "n"
(key-nth v 0) ; "a"
(val-nth v 0) ; 1

(assoc-after v "a" 2) ; (assoc v "aT" 2)
(assoc-before v "b" 2) ; (assoc v "aT" 2)
(assoc-prepend v 2) ; (assoc v "G" 2)
(assoc-append v 2) ; (assoc v "n" 2)
(assoc-nth v 0 2) ; (assoc v "a" 2)
(assoc-before-nth v 1 2) ; (assoc v "aT" 2)
(assoc-after-nth v 1 2) ; (assoc v "n" 2)

(get-min-key v) ; "a"
(get-max-key v) ; "b"
```

Charset:

```text
+-/0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz
```

### Workflow

Workflow https://github.com/minimal-xyz/minimal-shadow-cljs-nodejs

### License

MIT
