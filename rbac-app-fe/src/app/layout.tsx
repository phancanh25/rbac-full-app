// app/layout.tsx
import "./globals.css";
import Providers from "./Providers";
import Menu from "./Menu"; // Dynamic menu component

export const metadata = {
  title: "My RBAC App",
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <body>
        <Providers>
          <Menu />
          {children}
        </Providers>
      </body>
    </html>
  );
}
