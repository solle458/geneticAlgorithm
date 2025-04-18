# -*- coding: utf-8 -*-

import numpy as np
import matplotlib.pyplot as plt

def main():
    # 入力を受け取る
    N = int(input("頂点の数を入力してください: "))
    xs = []
    ys = []
    print("座標を1行ずつ入力してください (x y):")
    for _ in range(N):
        x, y = map(float, input().split())
        xs.append(x)
        ys.append(y)

    xs = np.array(xs)
    ys = np.array(ys)

    # 多項式近似（2次がデフォルト）
    degree = 2
    coeffs = np.polyfit(xs, ys, degree)
    poly = np.poly1d(coeffs)

    # プロット用のx値
    x_fit = np.linspace(min(xs), max(xs), 500)
    y_fit = poly(x_fit)

    # グラフ描画
    plt.scatter(xs, ys, color='blue', label='Score')
    plt.plot(x_fit, y_fit, color='red', label=f'{degree}cubic approximation')
    plt.title('Relationship between study time and score')
    plt.xlabel('Time')
    plt.ylabel('Score')
    plt.legend()
    plt.grid(True)
    plt.show()

if __name__ == '__main__':
    main()
